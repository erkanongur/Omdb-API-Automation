package base;

public class Enums {
	//Using Enums makes it easy to update API keys or URLs when they change.
	
	public enum KEYS{
		
		API_KEY("508070be");
		private String apiKey;
		
		KEYS(String apiKey){
			this.apiKey = apiKey;
		}
		public String getApiKey() {
			return apiKey;
		}
	}
	public enum URL{
		
		TARGET_URL("http://www.omdbapi.com/");
		private String targetURL;
		
		URL(String targetURL){
			this.targetURL = targetURL;
		}
		public String getTargetURL() {
			return targetURL;
		}
	}
}
