package com.web.controller;

import com.web.DAO.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by skandula on 3/14/16.
 */
@Controller
@RequestMapping(value = "/api/v1/")
public class AccountController {

    @Autowired
    private AccountDAO accountDAO;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "accounts", method = RequestMethod.GET)
    public String getAccounts(HttpServletRequest request) {
        request.setAttribute("accounts", accountDAO.findAll());
        return "accountsList.jsp";
    }

}
