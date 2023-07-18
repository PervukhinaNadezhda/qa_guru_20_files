package qa.guru;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class UnpackingZip {
    @Test
    void readingAndCheckingFilesInZipTest() throws Exception {

        ZipFile zf = new ZipFile("src/test/resources/example.zip");

        ZipEntry entryPdf = zf.getEntry("1_pdf.pdf");
        try (InputStream stream = zf.getInputStream(entryPdf)) {
            PDF pdf = new PDF(stream);
            Assertions.assertEquals("test \r\n", pdf.text);
        }

        ZipEntry entryXls = zf.getEntry("2_xl.xls");
        try (InputStream stream = zf.getInputStream(entryXls)) {
            XLS xls = new XLS(stream);
            Assertions.assertEquals("fruit", xls.excel
                    .getSheetAt(0)
                    .getRow(0)
                    .getCell(0)
                    .getStringCellValue());
        }

        ZipEntry entryCsv = zf.getEntry("3_qa_guru.csv");
        try (InputStream stream = zf.getInputStream(entryCsv)) {
            Reader reader = new InputStreamReader(stream);
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> content = csvReader.readAll();

            Assertions.assertEquals(3, content.size());

            final String[] firstRow = content.get(0);
            final String[] secondRow = content.get(1);
            final String[] thirdRow = content.get(2);

            Assertions.assertArrayEquals(new String[]{"Teacher", "lesson"}, firstRow);
            Assertions.assertArrayEquals(new String[]{"Tuchs", "Files"}, secondRow);
            Assertions.assertArrayEquals(new String[]{"Vasenkov", "REST Assured"}, thirdRow);
        }
    }
}
