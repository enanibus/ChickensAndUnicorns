package com.example.jacobo.chickensandunicorns.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by jacobo on 5/12/16.
 */

public class Menu {
    private static LinkedList<Course> sCourses = new LinkedList<>();
    private static final String MENU_URL = "http://www.mocky.io/v2/58470e903f0000920efe6950";

    public Menu(LinkedList<Course> sCourses) {
        Menu.sCourses = sCourses;
    }

    public static LinkedList<Course> getMenu() {
        return sCourses;
    }

    public void setsCourses(LinkedList<Course> sCourses) {
        Menu.sCourses = sCourses;
    }

    public static LinkedList<Course> downloadMenu() {
        try {
                URLConnection connection = new URL(MENU_URL).openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();

                JSONObject jsonRoot = new JSONObject(sb.toString());
                JSONArray menu = jsonRoot.getJSONArray("menu");
                for (int i = 0; i < menu.length(); i++) {
                    JSONObject currentCourse = menu.getJSONObject(i);

                    int id = currentCourse.getInt("id");
                    String name = currentCourse.getString("name");
                    String description = currentCourse.getString("description");
                    String type = currentCourse.getString("type");
                    double price = currentCourse.getDouble("price");
                    JSONArray arrayAllergens = currentCourse.getJSONArray("allergens");
                    ArrayList<String> allergens = new ArrayList<String>();
                    for (int j = 0; j < arrayAllergens.length(); j++) {
                        String currentAllergen = arrayAllergens.getString(j);
                        allergens.add(currentAllergen);
                    }
                    String imageURL = currentCourse.getString("image");


                    Course course = new Course(id, name, description, type, price, new URL(imageURL), null, allergens);
                    sCourses.add(course);
                }

                return sCourses;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
