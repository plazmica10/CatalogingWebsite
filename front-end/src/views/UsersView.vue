<template>
    <h1>Users</h1>
    <div class="row justify-content-center">
        <div class="d-flex justify-content-center">
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in users" :key="user.id">
                        <td>{{ user.username }}</td>
                        <td>{{ user.name }}</td>
                        <td>{{ user.surname }}</td>
                        <td>{{ user.description }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UsersView",

  data: () => ({
    users: [],
  }),

  mounted: function() {
    axios
        .get("http://localhost:9090/users")
        .then((res) => {
          this.users = res.data;
          console.log(this.users);
        })
        .catch((error) => {
          console.log(error);
        });
  }
};
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
  color: #ebebeb;
}
</style>