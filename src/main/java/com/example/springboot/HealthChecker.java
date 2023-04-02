package com.example.springboot;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class HealthChecker {
    @RequestMapping("/user")
    public String getHealth(){
        return "ok";
    }

    @RequestMapping("/time")
    public String getTime(@RequestParam(value = "timeZone") String timeZone){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date gmt = new Date(sdf.format(date));
        long seconds = gmt.getTime();
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;

        return String.format("%d:%02d:%02d", h,m,s);
    }


    @RequestMapping(value = "/math-service", headers="Content-Type=application/json", method = RequestMethod.POST)
    @ResponseBody
    public String getMath(@RequestBody String postRequest) throws IOException {
        JSONObject jsonObject = new JSONObject(postRequest);
        int number1 = jsonObject.getInt("firstNumber");
        int number2 = jsonObject.getInt("secondNumber");
        String operation = jsonObject.getString("operation");
        String jsonResponse = "{\n" +
                "  \"result\":%s\n" +
                "} ";

        switch (operation){
            case "sum":
                return String.format(jsonResponse,number1 + number2);
            case "multiply":
                return String.format(jsonResponse,number1 * number2);
            case "difference":
                return String.format(jsonResponse,number1 - number2);
            case "divide":
                return String.format(jsonResponse,number1/ number2);
            case "pow":
                return String.format(jsonResponse,Math.pow(number1,number2));

        }
        return "";
    }
}
