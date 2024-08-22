package geradorxml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.time.LocalDate;
import java.time.Month;

public class XML {
	
	    public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
	        Path p = Files.createFile(Paths.get(zipFilePath));
	        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
	            Path pp = Paths.get(sourceDirPath);
	            Files.walk(pp)
	              .filter(path -> !Files.isDirectory(path))
	              .forEach(path -> {
	                  ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
	                  try {
	                      zs.putNextEntry(zipEntry);
	                      Files.copy(path, zs);
	                      zs.closeEntry();
	                } catch (IOException e) {
	                    System.err.println(e);
	                }
	              });
	        }
	    }
	    public static void main(String args[]) throws IOException{
	    	 LocalDate today = LocalDate.now();
	         
	         // Obtenha o mês atual como um objeto Month
	         Month currentMonth = today.getMonth();
	         
	         // Obtenha o número do mês (1-12)
	         int numeroAno= today.getYear();
	         int numeroMes = today.getMonthValue();
	         
	         if (numeroMes == 1) {
	        	 numeroMes=12;
	        	 numeroAno--;
	         } else {
	        	 numeroMes--;
	         }
	         String usuario = System.getProperty("user.home");
	         String caminho = "C:\\SYMACWIN\\NFE\\001\\NFCe\\%d%02d";
	         String caminhoSaida = "%s\\Desktop\\%d%02d.zip";
	    	pack(
	    			String.format(caminho,numeroAno, numeroMes),
	    			String.format(caminhoSaida,usuario ,numeroAno, numeroMes)
	    	);
	    }
}
