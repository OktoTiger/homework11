import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;
import com.opencsv.CSVReader;


import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesOutArchive {
    private ClassLoader cl = FilesOutArchive.class.getClassLoader();

    private String arhiveFile = "zip.zip";

    @Test
    void getCvsFromZipFileTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(arhiveFile))){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().contains(".cvs")){
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> cvsData = csvReader.readAll();
                    assertThat(cvsData.get(20)).contains(";Invoice Subtotal; $1 115,00 ;");
                    return;
                }
            }
        }
    }
    @Test
    void getPdfFromZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(arhiveFile))){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().contains(".pdf")){
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("PDF");
                    return;
                }
            }

        }

    }

    @Test
    void getXlxFromZipTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(arhiveFile))){
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null){
                if (entry.getName().contains(".xlsx")){
                    XLS xls = new XLS(zis);
                    assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue()).isEqualTo("Test Company");
                return;
                }
            }

        }
    }





}
