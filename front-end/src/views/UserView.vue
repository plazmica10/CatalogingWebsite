<template>
    <div>
        <h1 v-if="user">{{ user.name }}</h1>
        <p v-if="user">Email: {{ user.email }}</p>
        <p v-if="user">Role: {{ user.role }}</p>
        <div v-if="user.role == 'AUTHOR'">
          <button class="btn" style="background-color: #252422; color: white;text-decoration:double;" @click="showDialog = true; console.log(showDialog)">Send request</button>
          <dialog v-if="showDialog" role="dialog" aria-modal="true" open>
            <form @submit.prevent="submitForm">
              <input type="email" v-model="email" placeholder="Email" required>
              <input type="phone" v-model="phone" placeholder="Phone">
              <input type="text" v-model="message" placeholder="Message">
              <button type="submit">Send</button>
            </form>
          </dialog>
        </div>
        <p v-if="!user">No user found.</p>
    </div>
<div class="container" style="border: 1px solid red;">
    <div v-for="shelf in shelves" :key="shelf.id">
      <ul class="list-group">
        <li class="list-group-item d-flex justify-content-between align-items-center">
          {{ shelf.name }}
          <span class="badge rounded-pill text-bg-primary">{{ shelf.shelfItems.length }}</span>
        </li>
        <ul class="list-group mx-4">
          <li v-for="shelfItem in shelf.shelfItems" :key="shelfItem.id" class="list-group-item list-group-item-light d-flex justify-content-between align-items-center">
            {{ shelfItem.book.title }}
          </li>
        </ul>
      </ul>
    </div>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserView",

  data: () => ({
    showDialog: false,
    email: "",
    phone: "",
    message: "",
    inShelf: {
      id: 0,
      isPrimary: false,
      name: "",
      shelfItems: []
    },
    shelves: [],
    user:{
      username: "",
      name: "",
      surname: "",
      email: "",
      description: "",
      role: "",
      shelves: []
    }
  }),
  mounted: function() {

    const userId = this.$route.params.id;
    axios
        .get("http://localhost:9090/users/" + userId, {withCredentials: true})
        .then((res) => {
            this.user = res.data;
            // console.log(this.user);
        })
        .catch((error) => {
        console.log(error);
        });
    axios
         .get("http://localhost:9090/shelves", {withCredentials: true})
         .then((res) => {
            this.shelves = res.data;
            console.log(this.shelves);

            this.shelves.forEach(shelf => {
    axios
                .get("http://localhost:9090/shelves/" + shelf.id, {withCredentials: true})
                .then((res) => {
                    shelf.shelfItems = res.data.shelfItems;
                    console.log(shelf.shelfItems);
                })
                .catch((error) => {
                    console.log(error);
                });
            });
         })
         .catch((error) => {
           console.log(error);
         });
  },
  methods: {
    submitForm() {
      axios
        .post(`http://localhost:9090/requests/${this.user.id}`, {
          email: this.email,
          phone: this.phone,
          message: this.message,
        }, { withCredentials: true })
        .then((res) => {
          window.alert("Request sent!");
          this.showDialog = false;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>

</style>