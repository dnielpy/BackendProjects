package com.example.demo.Series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {

    @Autowired
    SeriesRepository seriesRepository;

    //Crud operations
    public SeriesEntity createSeries(String name, String url) {
        SeriesEntity series = seriesRepository.findByName(name);
        if (series == null) {
            series = new SeriesEntity(name, url);
            seriesRepository.save(series);
            return series;
        } else {
            throw new IllegalArgumentException("Series already exists");
        }
    }

    public SeriesEntity getSeries(String name) {
        SeriesEntity series = seriesRepository.findByName(name);
        if (series == null) {
            throw new IllegalArgumentException("Series does not exist");
        }
        return series;
    }

    public List<SeriesEntity> getAllSeries() {
        List<SeriesEntity> seriesList = new ArrayList<>();
        try {
            // Parse the HTML file
            Document doc = Jsoup.parse(new File("API/src/main/java/com/example/demo/Series/ingles.txt"), "UTF-8");
            Elements series = doc.select("td a");
            for (Element serie : series) {
                String serieName = serie.text();
                if (!serieName.equals("Parent Directory")) {
                    // Remove the trailing slash
                    if (serieName.endsWith("/")) {
                        serieName = serieName.substring(0, serieName.length() - 1);
                    }
                    String serieUrl = "https://visuales.uclv.cu/Series/Ingles/" + serieName + "/";
                    SeriesEntity seriesEntity = new SeriesEntity(serieName, serieUrl);
                    seriesRepository.save(seriesEntity);
                    seriesList.add(seriesEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seriesList;
    }

    public void deleteSeries(String name) {
        SeriesEntity series = seriesRepository.findByName(name);
        if (series == null) {
            throw new IllegalArgumentException("Series does not exist");
        }
        seriesRepository.delete(series);
    }

    public SeriesEntity updateSeries(String name, String newName) {
        SeriesEntity series = seriesRepository.findByName(name);
        if (series == null) {
            throw new IllegalArgumentException("Series does not exist");
        }
        series.setName(newName);
        seriesRepository.save(series);
        return series;
    }

    public void updateContent() {
        String baseUrl = "https://visuales.uclv.cu/Series/Ingles/";
        try {
            Document doc = Jsoup.connect(baseUrl).get();
            Elements series = doc.select("td a");
            for (Element serie : series) {
                String serieName = serie.text();
                if (!serieName.equals("Parent Directory")) {
                    // Remove the trailing slash
                    if (serieName.endsWith("/")) {
                        serieName = serieName.substring(0, serieName.length() - 1);
                    }
                    String serieUrl = baseUrl + serieName  + "/";
                    createSeries(serieName, serieUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
