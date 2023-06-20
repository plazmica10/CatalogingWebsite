<template>
    <div class="wrapper">
        <form class="kanta" @submit.prevent="Register">
            <div v-if="error" class="alert alert-danger mx-2">
                {{ error }}
            </div>
        <div class="kanturina">
            <div class="form-group">
                <label>Name</label>
                <input name="name" type="text" class="inputField" id="exampleInputName1" v-model="registerDto.name">
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input name="surname" type="text" class="inputField" id="exampleInputSurname1" v-model="registerDto.surname">
            </div>
            <div class="form-group">
                <label>Username</label>
                <input name="username" type="text" class="inputField" id="exampleInputUsername1" v-model="registerDto.username" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input name="email" type="email" class="inputField" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="registerDto.email" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input name="password" type="password" class="inputField" id="exampleInputPassword1" v-model="registerDto.password" required>
            </div>
            <div class="form-group">
                <label>Confirm Password</label>
                <input name="confirmPassword" type="password" class="inputField" id="exampleInputConfirmPassword1" v-model="registerDto.confirmPassword" required>
            </div>
        </div>
            <button type="submit" class="button" :disabled="!passwordsMatch">Register</button>
        </form>
    </div>
</template>
    
    
<script>
    import axios from "axios";

    export default {
        name: "LoginView",
    
        data: () => ({
            registerDto: {
                name: '',
                surname: '',
                username: '',
                email: '',
                password: '',
                confirmPassword: ''
            },
            error: ''
        }),
        computed: {
        passwordsMatch() {
            if (this.registerDto.password !== this.registerDto.confirmPassword) {
                this.error = 'Passwords do not match';
                return false;
            } else {
                this.error = '';
                return true;
            }
        }
},
        methods: {
            async Register() {
                try {
                    const response = await axios.post("http://localhost:9090/users/register", this.registerDto, {withCredentials: true});
                    this.$store.commit("setLoggedIn", true);
                    this.$store.commit("setUser", response.data);
                    console.log("User: ", this.$store.state.user);
                    console.log("Registered: ", this.$store.state.loggedIn);
                    this.login = response.data;
                    console.log("Login: ", response);
                    this.$router.push({ name: "home", params: {} });
                } catch (error) {
                    if (error.response) {
                        console.log("Error status code: ", error.response.status);
                        console.log("Error response body: ", error.response.data);
                        this.error = error.response.data;
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
    
    .inputField, .button{
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