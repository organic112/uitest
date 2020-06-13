package org.example.sys.sys.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.TestException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class QueryRepository {

    private static final String QUERY_FILE_NAME = "queries.xml";
    private static final String QUERY_TAG = "query";
    private static final String ID_ATTRIBUTE = "id";
    private static HashMap<String, String> map = new HashMap<>();

    public static void loadQueries() {

        log.info("Reading queries file: " + QUERY_FILE_NAME);

        File file = CommonUtil.getFileFromResource(QUERY_FILE_NAME);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.normalize();

            NodeList nodeList = document.getElementsByTagName(QUERY_TAG);
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Element element = (Element) nodeList.item(i);
                String id = element.getAttribute(ID_ATTRIBUTE);
                String query = element.getTextContent();
                map.put(id, query);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new TestException("Error when parsing query file" + e.getLocalizedMessage());
        }
    }

    public static String getQuery(String queryId) {
        log.info("Reading query with id: " + queryId);

        if (null == map) {
            throw new TestException("Query map is null");
        }
        String query = map.get(queryId);
        if (null == query) {
            throw new TestException("Query not found with id:" + queryId);
        }
        return query;
    }
}
