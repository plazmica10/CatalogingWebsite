<template>
    <h1>Users</h1>
    <div class="row justify-content-center">
        <div class="d-flex justify-content-center">
            <table>
                <tbody>
                    <tr v-for="user in users" :key="user.id">
                        <td  @click="viewUser(user.id)"><img :src="userPhotoPath(user.photo)" alt="user image" style="width: 100px; height: 100px; border-radius: 50%;border: 1px solid #CCC5B9; margin: 10px; display: inline-block; cursor: pointer;"/></td>
                        <td>{{ user.username }}</td>
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
        // this.$router.push({ name: "UserView", params: { id: userId } });
        this.$router.push("/user?id=" + userId);
      }catch(error){
        console.log(error);
      }
    },
    userPhotoPath(photo) {
      try{
        if (photo == null) {
          return require("@/assets/user.png");
        }
        return require("@/assets/" + photo);
    }catch(error){
      return require("@/assets/user.png");
    }
    },
  },
};
</script>

<style>
</style>