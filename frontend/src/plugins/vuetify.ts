/**
 * plugins/vuetify.ts
 *
 * Framework documentation: https://vuetifyjs.com`
 */

// Styles
import '@mdi/font/css/materialdesignicons.css';
import 'vuetify/styles';

// Composables
import { createVuetify } from 'vuetify';

// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
export default createVuetify({
  display: {
    mobileBreakpoint: 'md',
  },
  defaults: {
    global: {
      ripple: false,
    },
    VBtn: {
      rounded: 'pill',
    },
    VTab: {
      rounded: 'pill',
      variant: 'plain',
    },
    VListItem: {
      rounded: 'pill',
    },
  },
  theme: {
    defaultTheme: 'light',
    themes: {
      light: {
        colors: {
          primary: '#5496f6',
          secondary: '#71a8f8',
          error: '#bf4747',
          success: '#4CAF50',
          warning: '#FB8C00',
          strong: '#212121',
          background: '#ffffff',
          'background-content': '#f2f2f2',
        },
      },
      dark: {
        colors: {
          primary: '#5496f6',
          secondary: '#71a8f8',
          error: '#bf4747',
          success: '#4CAF50',
          warning: '#FB8C00',
          strong: '#F5F5F5',
          background: '#181818',
          'background-content': '#121212',
        },
      },
    },
  },
});
