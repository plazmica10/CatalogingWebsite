<template>
    <div>
        <button v-if="this.$store.state.user.role == 'ADMIN' && this.$store.state.loggedIn" class="btn" style="background-color: #252422; color: white;text-decoration:double;height: 3em;margin:10px;" @click="showDialog = !showDialog">Add Genre</button>
                <dialog v-if="showDialog" role="dialog" aria-modal="true" open style="z-index: 9999;">
                    <form @submit.prevent="submit">
                        <input type="text" v-model="name" placeholder="Name" required>
                        <button type="submit">Add</button>
                </form>
            </dialog>
            <h2>Genres</h2>
        <div class="card mx-auto bg-dark" style="width: 18rem;">
          <ul class="list-group list-group-flush">
            <li class="list-group-item" v-for="genre in genres" :key="genre.id">{{ genre.name }}</li>
          </ul>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "GenresView",

    data: () => ({
        showDialog: false,
        searchText: "",
        genres: [],
        name: "",
    }),

    mounted: function() {
        axios
            .get("http://localhost:9090/genres")
            .then((res) => {
            this.genres = res.data;
            console.log(this.genres);
            })
            .catch((error) => {
            console.log(error);
            });
    },
    methods: {
  submit() {
    fetch("http://localhost:9090/genres", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name: this.name }),
      credentials: "include",
    })
      .then((res) => {
        if (res.ok) {
          this.showDialog = false;
          location.reload();
        } else {
          throw new Error("Failed to add genre");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
},
};
</script>