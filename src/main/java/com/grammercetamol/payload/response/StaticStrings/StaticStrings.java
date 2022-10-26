package com.grammercetamol.payload.response.StaticStrings;

public class StaticStrings {

    public static String REGISTRATION_SUCCESSFULLY = "User Registered Successfully";
    public static String LOGIN_SUCCESS = "Login Successfully";
    public static int SUCCESSCODE = 0;
    public static int ERRORCODE = 1;

    public static String MAIL_CONFIRMATION = "mail confirmation";

    public static String MESSAGE(String name, String link, String token){
//        String link1 = "http://localhost:8001/api/v1/registration/confirmToken?token="+link;
        String li = link +token;
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div style=\"background-color: #ecd5d5; color: black\">\n" +
                "      <h1 style=\"align-items: center\">Email Confirmation</h1>\n" +
                "      <p>Dear " + name + "</p>\n" +
                "      <p>Click the link below to confirm your email</p>\n" +
                "      <p><a href="+ li + ">this is the link below\n" +li+"</a></p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";
    }
}
