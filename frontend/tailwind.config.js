/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {},
  },
  plugins: [require('@tailwindcss/typography'), require('daisyui')],
  daisyui: {
    themes: [
      'light',
      'dark',
      'forest',
      'corporate',
      'wireframe',
      'cupcake',
      'retro',
      'aqua',
      'business',
      'black',
      'winter',
      'acid',
      {
        army: {
          ...require('daisyui/src/theming/themes')['[data-theme=corporate]'],
          'font-family': 'GI, Overpass, sans-serif',
          primary: '#d51e4a',
          secondary: '#221f20',
          accent: '#221f20',
          neutral: '#ef4444',
          // neutral: '#333c33',
          // neutral: '#2f372f',
          // 'base-100': '#f9f9fb',
          info: '#46b6d2',
          success: '#5fddc6',
          warning: '#f5c72e',
          error: '#f8628d',
        },
      },
    ],
  },
};