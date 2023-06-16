<template>
    <div>
        <h1 v-if="user">{{ user.name }}</h1>
        <p v-if="user">Email: {{ user.email }}</p>
        <p v-if="user">Role: {{ user.role }}</p>
        <p v-else>No user found.</p>
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
    inShelf: {
      id: 0,
      isPrimary: false,
      name: "",
      shelfItems: []
    },
    shelves: [],
    user: null,
  }),
  mounted: function() {
    const userId = this.$store.state.user.id;
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
            // console.log(this.shelves);

            this.shelves.forEach(shelf => {
    axios
                .get("http://localhost:9090/shelves/" + shelf.id, {withCredentials: true})
                .then((res) => {
                    shelf.shelfItems = res.data.shelfItems;
                    // console.log(shelf.shelfItems);
                })
                .catch((error) => {
                    console.log(error);
                });
            });
         })
         .catch((error) => {
           console.log(error);
         });
  }
};
</script>

<style>

</style>