package plaid.apps.webserver;

//TODO - Temporary class, will be removed by coolstuffs from Plaid
public JavaHelper{
	
	public static String getRequestFromStream(InputStream input){
		StringBuffer request = new StringBuffer(2048);
	    int i;
	    byte[] buffer = new byte[2048];
	    try {
	      i = input.read(buffer);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	      i = -1;
	    }
	    for (int j=0; j<i; j++) {
	      request.append((char) buffer[j]);
	    }
	    return request.toString();
	}
	
	private String parseUri(String requestString) {
	    int index1, index2;
	    index1 = requestString.indexOf(' ');
	    if (index1 != -1) {
	      index2 = requestString.indexOf(' ', index1 + 1);
	      if (index2 > index1)
	        return requestString.substring(index1 + 1, index2);
	    }
	    return null;
	  }
}