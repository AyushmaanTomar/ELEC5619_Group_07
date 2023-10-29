import axios from 'axios';

const OPENAI_API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";

async function checkForModeration(text: string): Promise<boolean> {
    const response = await axios.post(
        OPENAI_API_ENDPOINT,
        { prompt: `Is this text safe for all audiences? ${text}`, max_tokens: 10 },
        {
            headers: {
                'Authorization': `Bearer YOUR_OPENAI_API_KEY`,
                'Content-Type': 'application/json',
            },
        }
    ).catch((error) => { throw error; })

    const answer = response.data.choices[0].text.trim().toLowerCase();
    return answer === "yes";
}


export default checkForModeration;