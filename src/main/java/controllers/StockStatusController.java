package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.category.ListCategoryDto;
import dto.stockstatus.ListStockStatusDto;
import dto.stockstatus.SingleStockStatusDto;
import dto.stockstatus.StockStatusDto;
import ejb.StockStatusBean;
import helpers.GetRequestParamsHelper;
import models.StockStatus;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/stock_status"})
public class StockStatusController extends HttpServlet {

    @EJB
    private StockStatusBean stockStatusBean;

    /**
     * List all stock status
     * Find stock status by id
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String msg = "";
        String data = "";
        boolean found = true;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode stockStatusNode = mapper.createObjectNode();
        if (StringUtils.isBlank(id)) {
            List<StockStatus> stocks = null;
            try {
                stocks = this.stockStatusBean.list();
                if(stocks.isEmpty()){
                    found = false;
                    msg="Stocks status not found";
                }
            } catch (Exception ex) {
                msg = ex.getMessage();
                found = false;
                ex.printStackTrace();
            } finally {
                if (!found) {
                    stockStatusNode.put("msg", msg);
                    stockStatusNode.put("stocksFound", false);
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockStatusNode);
                } else
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.buildStockListJson(stocks));

                response.getWriter().println(data);
            }
        } else {
            StockStatus stock = new StockStatus();
            try {
                stock = this.stockStatusBean.show(id);
            } catch (Exception ex) {
                msg = ex.getMessage();
                found = false;
                ex.printStackTrace();
            } finally {
                if (!found) {
                    stockStatusNode.put("msg", msg);
                    stockStatusNode.put("stockFound", false);
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockStatusNode);
                } else
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.buildStockJson(stock));

                response.getWriter().println(data);
            }
        }
    }

    /**
     * Create new stock status
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String msg = "";
        boolean stockCreated = false;

        try{
            this.stockStatusBean.create(GetRequestParamsHelper.getRequestParams(request));
            msg = "Stock status created successfully";
            stockCreated = true;
        }catch (Exception ex) {
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode stockStatusNode = mapper.createObjectNode();
            stockStatusNode.put("msg",msg);
            stockStatusNode.put("stockCreated", stockCreated);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockStatusNode);
            response.getWriter().println(data);
        }
    }

    /**
     * Update stock status
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String msg = "";
        boolean stockUpdated = false;
        try{
            this.stockStatusBean.update(GetRequestParamsHelper.getRequestParams(request));
            msg = "Stock Status updated successfully";
            stockUpdated = true;
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode stockStatusNode = mapper.createObjectNode();
            stockStatusNode.put("msg",msg);
            stockStatusNode.put("stockUpdated", stockUpdated);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockStatusNode);
            response.getWriter().println(data);
        }
    }

    /**
     * Delete stock status
     * @param request
     * @param response
     * @throws IOException
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String msg = "";
        boolean stockDeleted = false;
        try{
            this.stockStatusBean.delete(id);
            msg = "Stock status deleted successfully";
            stockDeleted = true;
        }catch (Exception ex){
            msg = ex.getMessage();
            ex.printStackTrace();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode stockStatusNode = mapper.createObjectNode();
            stockStatusNode.put("msg",msg);
            stockStatusNode.put("stockDeleted", stockDeleted);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stockStatusNode);
            response.getWriter().println(data);
        }
    }

    /**
     * build stock status json
     * @param stockStatus
     * @return
     */
    public SingleStockStatusDto buildStockJson(StockStatus stockStatus){
        return new SingleStockStatusDto(new StockStatusDto(stockStatus));
    }

    /**
     * build stock status list json
     * @param stockStatuses
     * @return
     */
    public ListStockStatusDto buildStockListJson(List<StockStatus> stockStatuses){
        return new ListStockStatusDto(
                stockStatuses.stream().map(s -> new StockStatusDto(s)).collect(Collectors.toList())
        );
    }
}
