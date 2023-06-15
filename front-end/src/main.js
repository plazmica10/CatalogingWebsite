import { createApp, h } from 'vue'
import { createStore } from "vuex"
import createPersistedState from 'vuex-persistedstate'
import App from './App.vue'
import router from './router'

//bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
//icons - fontawesome
/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'
/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
/* import specific icons */
import { faMagnifyingGlass, faUserSecret } from '@fortawesome/free-solid-svg-icons'

const store = createStore({
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })],
    state: {
        searchText: '',
        loggedIn: false,
        user: null,
    },
    mutations: {
        setSearchText(state, searchText) {
            state.searchText = searchText;
        },
        setLoggedIn(state, loggedIn) {
            state.loggedIn = loggedIn;
        },
        setUser(state, user) {
            state.user = user;
        }
    }
});

/* add icons to the library */
library.add(faUserSecret,faMagnifyingGlass)

createApp(App).use(router).use(store).component('font-awesome-icon', FontAwesomeIcon).mount('#app')
// createApp({
//     render: () => h(App),
//     router,
//     store
//   }).component('font-awesome-icon', FontAwesomeIcon).mount('#app')
