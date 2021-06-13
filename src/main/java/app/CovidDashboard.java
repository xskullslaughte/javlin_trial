package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class CovidDashboard implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/index_covid.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("index_covid.html");

    @Override
    public void handle(Context context) throws Exception {
        // The model of data to provide to Thymeleaf.
        // In this example the model will be filled with:
        //  - Title to give to the h1 tag
        //  - Array list of all movies for the UL element
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        // model.put("title", new String("All Movies in the Database"));

        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the movies
        ArrayList<String> country = jdbc.country_name_covid();
        System.out.println(country);
        // Finally put all of these movies into the model
        model.put("types",country);
        String country_selected = context.formParam("covid_country");
        System.out.println(country_selected + " Country is selected");
        model.put("country_name", country_selected);



        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);
    }

}
