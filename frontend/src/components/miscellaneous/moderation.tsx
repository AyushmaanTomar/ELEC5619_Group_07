import axios from 'axios';

const checkForModeration = async (text: string) => {
  try {
    const response = await fetch("https://moderationapi.com/api/v1/moderation/text?value=" + text, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1M2U2NDIzMWU1NzY2NzViNWMwYTAxMyIsInVzZXJJZCI6IjY1M2U2M2I3MzhmMTI0ZTkxNDQ3YTJhOSIsImlhdCI6MTY5ODU4NzY4M30.EAQyOqLNf2p45EyINnd-gr3X1PkwvlnrHEPuhPP6yCQ",
      },
      body: JSON.stringify({
        value: text,
      }),
    })

    const data = await response.json();
    const { flagged, content } = data;

    return flagged ;
    // return response.data.safe;

  } catch (error) {
    console.error("Error checking content for moderation:", error);
    throw error;
  }
};

export default checkForModeration;