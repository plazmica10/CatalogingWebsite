import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BooksView from '../views/BooksView.vue'
import BooksSearchView from '../views/BooksSearchView.vue'
import GenresView from '../views/GenresView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/books',
    name: 'books',
    component: BooksView
  },
  {
    path: '/search',
    name: 'BooksSearchView',
    component: BooksSearchView
  },
  {
    path: '/genres',
    name: 'genres',
    component: GenresView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router