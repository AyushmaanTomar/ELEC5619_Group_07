import axios from 'axios';

const checkForModeration = async (text: string) => {
  try {
    const response = await axios.post('YOUR_RAPID_API_ENDPOINT', {
      // Your payload if necessary. It's just an example; adjust it based on your API documentation.
      text: text,
    }, {
      headers: {
        'Authorization': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1M2UzNDRjMTFkMjMyOWQ4MjlmOTk2NCIsInVzZXJJZCI6IjY1M2UzNDBjNGE5ZDk5OTJlZWUxOWVmNCIsImlhdCI6MTY5ODU3NTQzNn0.DtaU8L_Kh_gQRG7rxxrEWEwZHq-Llz6ZHni-4QD2S3M',
        'Content-Type': 'application/json',
      }
    });

    // Interpret the response. This depends on the API's response structure.
    // For example, if the API returns a boolean `safe` field:
    return response.data.safe;

  } catch (error) {
    console.error("Error checking content for moderation:", error);
    throw error;
  }
};

export default checkForModeration;