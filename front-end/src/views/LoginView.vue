<template>
<h1>Sign In</h1>

<div class="wrapper">
    <form class="kanta" @submit.prevent="Login">
        <div v-if="error" class="alert alert-danger mx-2">
            {{ error }}
        </div>
    <div class="kanturina">
        <div class="form-group">
            <label>Email</label>
            <input name="email" type="email" class="" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="loginDto.email">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input name="password" type="password" class="" id="exampleInputPassword1" v-model="loginDto.password">
        </div>
    </div>
        <button type="submit" class="button">Sign in</button>
    </form>
</div>
</template>


<script>
import axios from "axios";

export default {
    name: "LoginView",

    data: () => ({
        loginDto: {
            email: '',
            password: ''
        },
        error: ''
    }),

    methods: {
        async Login() {
            try {
                const response = await axios.post("http://localhost:9090/users/login", this.loginDto, {withCredentials: true});
                this.$store.commit("setLoggedIn", true);
                // console.log("Logged in: ", this.$store.state.loggedIn);
                this.$store.commit("setUser", response.data);
                this.$router.push({ name: "home", params: {} });
            } catch (error) {
                if (error.response) {
                    console.log("Error status code: ", error.response.status);
                    console.log("Error response body: ", error.response.data);
                    if (error.response.status == 403)
                        this.error = "You are already logged in!";
                    if (error.response.status == 400)
                        this.error = "Invalid credentials!";
                } else {
                    console.log("Error: ", error.message);
                    this.error = error.message;
                }
            }
        }
    }
};
</script>

<style>
.wrapper{
    display: flex;
    justify-content: center;
    align-items: center;
}

#exampleInputEmail1,#exampleInputPassword1, .button{
    width: 80%;
    border-radius: 10px;
    padding: 5px;
    border: 1px solid #CCC5B9;
}
.form-group{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 10px;
}

.kanta{
    width: 20%; 
    padding-top: 10px;
    padding-bottom: 5px;
    margin-top: 10%;
    border: 1px solid #CCC5B9;
    border-radius: 10px;
}
.kanturina{
    margin-left: 15%;
}
.button{
    margin-top: 20px;
    background-color: #252422;
    color: #ccc;
    border: transparent;
}
</style>