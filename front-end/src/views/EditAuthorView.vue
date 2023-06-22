<template>
<form @submit.prevent="submitForm" style="" class="forneti"> 
  <div class="form-group">
    <input type="name" class="form-control" v-model="name" id="namee" placeholder="Name" required>
  </div>
  <div class="form-group">
    <input type="surname" class="form-control" v-model="surname" id="snamee" placeholder="Surname" required>
  </div>
  <div class="form-group">
    <input type="surname" class="form-control" v-model="username" id="unamee" placeholder="Username" required>
  </div>
  <div class="form-group">
    <textarea class="form-control" id="descriptionn" v-model="description" rows="3" placeholder="Description"></textarea>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</template>


<script>
import router from "@/router";
import axios from "axios";
export default {
    name: "EditAuthorView",

    data: () => ({
        name: "",
        surname: "",
        description: "",
        username: "",
    }),

    methods: {
        submitForm() {
            const authorId = this.$route.params.id;
            axios
                .put("http://localhost:9090/authors/" + authorId,{name:this.name,surname:this.surname,username:this.username,description:this.description}, {withCredentials: true})
                .then((res) => {
                    router.push({ name: "userse" });
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    },
};
</script>
<style>
.forneti{
    margin: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>