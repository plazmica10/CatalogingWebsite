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
                        <th>Role</th>
                        <th v-if="this.$store.state.loggedIn && this.$store.state.user.role == 'ADMIN'">Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in users" :key="user.id">
                      <td><a href="#" @click="viewUser(user.id)">{{ user.username }}</a></td>
                        <td>{{ user.name }}</td>
                        <td>{{ user.surname }}</td>
                        <td>{{ user.description }}</td>
                        <td>{{ user.role }}</td>
                        <td v-if="this.$store.state.loggedIn && this.$store.state.user.role == 'ADMIN' && user.role =='AUTHOR'"><router-link :to="{ name: 'edit-author', params: { id: user.id }}">Edit Author</router-link></td>
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
    authors: [{
      status: "",
    }],
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
  },
  methods: {
    viewUser: function (userId) {
      try{
        this.$router.push({ name: "UserView", params: { id: userId } });
      }catch(error){
        console.log(error);
      }
    },
  },
};
</script>

<style>
</style>