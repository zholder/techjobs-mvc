package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController extends TechJobsController {


    @RequestMapping(value = "")
    public String list(Model model) {

        model.addAttribute("columns", getColumnChoices());

        return "list";
    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam String column) {

        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            return "list-jobs";
        } else {
            ArrayList<String> items = JobData.findAll(column);
            model.addAttribute("title", "All " + getColumnChoices().get(column) + " Values");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            return "list-column";
        }

    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Jobs with " + getColumnChoices().get(column) + ": " + value);
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}
