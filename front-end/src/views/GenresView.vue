<template>
    <div>
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
        searchText: "",
        genres: [],
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
    }
};
</script>