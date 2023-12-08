package tomato;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;
/**
 * Class responsible for retrieving random games from an external API.
 */

public class GameServer {

	 // Reads content from the specified URL
	private static String readUrl(String urlString)  {
		try {
			URL url = new URL(urlString);
			InputStream inputStream = url.openStream();

			// Read the content from the URL
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			return result.toString("UTF-8");
		} catch (Exception e) {
			 // Handle exceptions if any
			
			System.out.println("An Error occured: " + e.toString());
			e.printStackTrace();
			return null;
		}

	}

	/**
     * Retrieves a random game from an external API.
     *
     * @return Game object with image and solution
     */
	public Game getRandomGame() {
		  // URL of the external API for getting random games
		// See http://marconrad.com/uob/tomato for details of usage of the api. 
		
		String tomatoapi = "https://marcconrad.com/uob/tomato/api.php?out=csv&base64=yes";
        // Retrieve raw data from the API

		String dataraw = readUrl(tomatoapi);
		// Split the raw data into an array
		String[] data = dataraw.split(",");
		// Decode the Base64-encoded image data
		byte[] decodeImg = Base64.getDecoder().decode(data[0]);
		ByteArrayInputStream quest = new ByteArrayInputStream(decodeImg);

		int solution = Integer.parseInt(data[1]);

		BufferedImage img = null;
		try {
			img = ImageIO.read(quest);
			// Create and return a new Game object with the retrieved image and solution
			return new Game(img, solution);
		} catch (IOException e1) {
			// TODO Add proper exception handling. 
			e1.printStackTrace();
			return null;
		}
	}

}
