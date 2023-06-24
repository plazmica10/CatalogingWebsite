<template>
  <div class="col-lg-2 mx-auto">
    <!-- <div style="border: 1px solid red; padding: 50px" class=""> 
      <img :src="user.photo" alt="user photo" style="width: 100px; height: 100px; border-radius: 50%;border: 1px solid #CCC5B9; margin: 10px; display: inline-block"/>
      <div style="display: inline-block; vertical-align: top; margin-left: 10px;">
        <h1 v-if="user">{{ user.name }}</h1>
        <p v-if="user">Email: {{ user.email }}</p>
        <p v-if="user">Role: {{ user.role }}</p>
      </div>
    </div> -->
    <div class="card mb-4 border-dark border-3" style="margin: 10px">
          <div class="card-body text-center">
            <img :src="userPhotoPath(user.photo)" alt="user photo" style="width: 100px; height: 100px; border-radius: 50%;border: 1px solid #CCC5B9; margin: 10px; display: inline-block"/>
            <h5 class="my-3" v-if="user">{{user.name}}</h5>
            <p class="text-muted mb-1" v-if="user">{{user.role}}</p>
            <p class="text-muted mb-4" v-if="user">{{user.email}}</p>

          </div>
        </div>
        <div v-if="user.role == 'AUTHOR'">
          <td class="btn btn-dark text-white" style="margin: 5px; display: inline-block;" v-if="this.$store.state.loggedIn && this.$store.state.user.role == 'ADMIN' && user.role =='AUTHOR'"><router-link :to="{ name: 'edit-author', params: { id: user.id }}" style="text-decoration: none; color: white">Edit Author</router-link></td>
          <button v-if="!this.$store.state.loggedIn" class="btn" style="background-color: #252422; color: white;text-decoration:double;" v-on:click="showSendRequest()">Send request</button>
          <dialog class="" id="sendRequest">
            <form @submit.prevent="submitForm">
              <input type="email" v-model="email" placeholder="Email" required>
              <input type="phone" v-model="phone" placeholder="Phone">
              <input type="text" v-model="message" placeholder="Message">
              <button type="submit" v-on:click="closeRequest()">Send</button>
            </form>
          </dialog>
        </div>
        <p v-if="!user">No user found.</p>
        <button v-if="this.$store.state.user.id == this.$route.query.id" class="btn btn-primary" v-on:click="showUpdateUser()">Edit</button>
    </div>
<div class="container">
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


<dialog id="updateUserfModal" style="width: 40%;">
    <h1>Update User</h1>
    <!-- <div v-if="error" class="alert alert-danger mx-2">
                {{ error }}
            </div> -->
    <form @submit.prevent="updateUser()">
      <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input v-model="user.name" type="text" class="form-control" id="name">
      </div>
      <div class="mb-3">
        <label for="surname" class="form-label">Surname</label>
        <input v-model="user.surname" type="text" class="form-control" id="surname">
      </div>
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input v-model="user.username" type="text" class="form-control" id="username">
      </div>
      <div class="mb-3">
        <label for="date" class="form-label">Bith date</label>
        <input v-model="user.date" type="date" class="form-control" id="date">
      </div>
      <div class="mb-3">
        <label for="photo" class="form-label">Photo</label>
        <input v-model="user.photo" type="text" class="form-control" id="photo">
      </div>
      <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea v-model="user.description" class="form-control" id="description"></textarea>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <input v-model="user.email" type="email" class="form-control" id="email">
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input v-model="user.password" type="password" class="form-control" id="password">
      </div>
      <div class="mb-3">
        <label for="passwordConf" class="form-label">Confirm password</label>
        <input v-model="confirmPass" type="password" class="form-control" id="passwordConf">
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <button v-on:click="closeUpdateUser()">Close</button>
</dialog>
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
    file: null,
    shelves: [],
    user:{
      username: "",
      name: "",
      surname: "",
      email: "",
      description: "",
      role: "",
      shelves: [],
      date: null,
      photo: "",
      password: "",
    },
    confirmPass: "",
    error: "",
  }),

  computed: {
    passwordsMatch() {
        if (this.user.password !== this.confirmPass) {
            this.error = 'Passwords do not match';
            return false;
        } else {
            this.error = '';
            return true;
        }
    }
  },

  mounted: function() {

    // const userId = this.$route.params.id;
    const userId = this.$route.query.id;

    axios
        .get("http://localhost:9090/users/" + userId, {withCredentials: true})
        .then((res) => {
            this.user = res.data;
            this.newUser = new Object(this.user);
            // console.log(this.user);
        })
        .catch((error) => {
        console.log(error);
        });

    axios
         .get("http://localhost:9090/shelves/user/" + userId)
         .then((res) => {
            this.shelves = res.data;
            this.shelves.
            console.log(this.shelves);

            this.shelves.forEach(shelf => {
              axios
                .get("http://localhost:9090/shelves/" + userId, {withCredentials: true})
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
    const sendRequest = document.getElementById("sendRequest");
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
    showSendRequest() {
      const sendRequest = document.getElementById("sendRequest");
      sendRequest.addEventListener("click", (e) => {
            const dialogDimensions = sendRequest.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
              sendRequest.close();
            }
        });
      sendRequest.showModal();
    },
    closeRequest() {
      const sendRequest = document.getElementById("sendRequest");
      sendRequest.close();
    },

    showUpdateUser() {
      const updateUserfModal = document.getElementById("updateUserfModal");
      updateUserfModal.addEventListener("click", (e) => {
            const dialogDimensions = updateUserfModal.getBoundingClientRect();
            if (e.clientX < dialogDimensions.left || e.clientX > dialogDimensions.right || e.clientY < dialogDimensions.top || e.clientY > dialogDimensions.bottom) {
              updateUserfModal.close();
            }
        });
      updateUserfModal.showModal();
    },
    closeUpdateUser() {
      const updateUserfModal = document.getElementById("updateUserfModal");
      updateUserfModal.close();
    },

    updateUser() {
      axios
        .put(`http://localhost:9090/users/${this.user.id}`, this.user, { withCredentials: true })
        .then((res) => {
          window.alert("User updated!");
          this.showDialog = false;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    userPhotoPath(photo) {
      try {
        return require(`../assets/${photo}`);
      } catch (err) {
        return require("@/assets/user.png");
      }
    },
  },
};
</script>

<style>

</style>