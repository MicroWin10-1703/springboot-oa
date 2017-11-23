package com.jk.controller;



import com.jk.model.Stock;
import com.jk.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/11/14.
 */
@Controller
public class StockController {

    @Resource
    private StockService stockService;

    /**
     * 查询进货信息
     *
     * @param model
     * @return
     */

    @RequestMapping("/findAll")
    public String stockList(Model model) {
        List<Stock> list = stockService.stockList();
        model.addAttribute("list", list);
        return "stock/list";
    }

    @Transactional
    @RequestMapping("/delete")
    public String deleteById(Integer id) {
        stockService.deleteById(id);
        return "redirect:/list";
    }

    @RequestMapping("/toAdd")
    public String addOne() {
        return "/stock/addStock";
    }

    @RequestMapping("/add")
    public void saveStock(Stock stock, @RequestParam Map<String, String> link) throws ParseException {
        if (stock != null) {
            java.util.Random r = new java.util.Random();
            for (int i = 0; i < 10; i++) {
                stock.setPurchaseID(r.nextLong());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stock.setPurchasetime(sdf.parse(link.get("addpurchasetime")));
            stock.setRegisterflag(1);
            stock.setFlag(0);
            stockService.saveStock(stock);
        }

    }

}
