import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BooksView from '../views/BooksView.vue'
import BooksSearchView from '../views/BooksSearchView.vue'
import GenresView from '../views/GenresView.vue'
import UsersView from '../views/UsersView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import ShelvesView from '../views/ShelvesView.vue'
import UserView from '../views/UserView.vue'
import BookView from '../views/BookView.vue'
import AuthorRegisterView from '../views/AuthorRegisterView.vue'
import RequestsView from '../views/RequestsView.vue'
import EditAuthorView from '../views/EditAuthorView.vue'
import BookAddView from '../views/BookAddView.vue'
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
  },
  {
    path: '/users',
    name: 'userse',
    component: UsersView
  },
  {
    path: '/user',
    name: 'UserView',
    component: UserView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/shelves',
    name: 'shelves',
    component: ShelvesView
  },
  {
    path: '/book',
    name: 'book',
    component: BookView
  },
  {
    path: '/create-author',
    name: 'author',
    component: AuthorRegisterView
  },
  {
    path: '/requests',
    name: 'requests',
    component: RequestsView
  },
  {
    path: '/edit-author',
    name: 'edit-author',
    component: EditAuthorView
  },
  {
    path: '/book-add',
    name: 'book-add',
    component: BookAddView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router