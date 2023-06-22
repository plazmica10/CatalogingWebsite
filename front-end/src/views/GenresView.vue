<template>
    <div>
        <button v-if="this.$store.state.user.role == 'ADMIN'" class="btn" style="background-color: #252422; color: white;text-decoration:double;height: 3em;" @click="showDialog = !showDialog">Add Genre</button>
                <dialog v-if="showDialog" role="dialog" aria-modal="true" open>
                    <form @submit.prevent="submit">
                        <input type="text" v-model="name" placeholder="Name" required>
                        <button type="submit">Add</button>
                </form>
            </dialog>
        <div class="row justify-content-center">
        <div class="d-flex justify-content-center">
            <table>
            <thead>
            <tr>
                <th>Genre</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="genre in genres" :key="genre.id">
                <td>{{ genre.name }}</td>
            </tr>
            </tbody>
            </table>
        </div>
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