import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

// This class is used for functionality involved in exporting
// data to a CSV file
public class CSVExporter {
	Series[] data;
	public CSVExporter(Series[] series) {
		data = series;
	}

	// Main export function for exporting data to a CSV file
	public void export(String filename) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(filename));
		
		for(int i = 0; i < data.length; i++) {
			if(data[i] == null || data[i].m_data == null) {
				continue;
			}
				
			for(int j = 0; j < data[i].m_data.size(); j++) {
				List<String> entry = new ArrayList<String>();
				entry.add(String.valueOf(i));
				entry.add(String.valueOf(data[i].m_data.get(j).x));
				entry.add(String.valueOf(data[i].m_data.get(j).y));
				writer.writeNext(entry.toArray(new String[0]));
			}
		}
		
		writer.close();          
	}
}
