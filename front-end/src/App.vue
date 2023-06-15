<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">BetterReads</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/books">Books</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/genres">Genres</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/users">Users</a>
          </li>
          <li class="nav-item" v-if="this.$store.state.loggedIn">
            <a class="nav-link active" aria-current="page" href="/shelves">Shelves</a>
          </li>
        </ul>
      </div>
      <div class = "searchBox">
        <form>
            <input autocomplete="off" name="q" type="text" placeholder="Search books" v-model="searchText">
          <button type="submit" v-on:click="SearchBooks">
            <font-awesome-icon icon="magnifying-glass" />
          </button>
        </form>
      </div>
      <span v-if="!this.$store.state.loggedIn">
        <a class="lgn" href="/login?">Login</a>
        <a class="lgn" href="/register">Register</a>
      </span>
      <span v-if="this.$store.state.loggedIn">
        <button class="lgn" style="background: transparent; border: none;" v-on:click="logout">Logout</button>
      </span>
    </div>
  </nav>
  <router-view/>
</template>

<script>
import axios from "axios";
//bla
export default {
  data() {
    return {
      searchText: ''
    }
  },
  methods: {
    SearchBooks: function() {
      this.$store.commit("setSearchText", this.searchText);
      this.$router.push({name: "BooksSearchView", params: {searchText: this.searchText}});
    },
    logout: function() {
      this.$store.commit("setLoggedIn", false);
      console.log(this.$store.state.logedIn);
      axios
        .post("http://localhost:9090/users/logout", {}, {withCredentials: true})
        .then((res) => {
          console.log(res);
          this.$router.push({name: "books"});
        })
        .catch((error) => {
          if (error.response) {
            console.log("Error status code: ", error.response.status);
            console.log("Error response body: ", error.response.data);
          } else {
            console.log("Error: ", error.message);
          }
          this.$router.push({name: "books"});
        });
    }
  }
}
</script>

<style>

body{
  background-color: #FFFCF2 !important;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

.lgn{
  margin: 10px;
  color: #EB5E28;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #42b983;
}
font-awesome-icon{
  margin: 10px;
  color: #EB5E28;
}
div router-link{
  margin: 10px;
}
.searchBox {
  display: flex;
  align-items: center;
  margin-right: 10px;
  padding: 4px 26px 4px 8px;
  border: 1px solid #ccc;
  border-radius: 20px;
  width: 300px;
  position: relative;
}

.searchBox input {
  flex: 1;
  border: none;
  outline: none;
  padding: 3px;
  background-color: transparent;
  caret-color: white;
  color: white;
}
.searchBox button{
  background-color: transparent;
  position: absolute;
  top: 50%;
  right: 10px; /* adjust as needed */
  transform: translateY(-50%);
  border: none;
  color: #ccc;
}
.searchBox font-awesome-icon {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: 10px;
  color: #ccc;
}
</style>
