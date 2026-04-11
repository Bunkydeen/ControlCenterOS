package com.controlcenter.shizuku;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShizukuRunner {
    public static String run(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec("sh -c " + cmd);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            return output.toString();

        } catch (Exception e) {
            Log.e("ShizukuRunner", e.toString());
            return "ERROR";
        }
    }
}
