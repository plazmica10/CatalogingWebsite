<template>
    <div class="wrapper">
        <form class="kanta" @submit.prevent="Register">
            <div v-if="error" class="alert alert-danger mx-2">
                {{ error }}
            </div>
        <div class="kanturina">
            <div class="form-group">
                <label>Name</label>
                <input name="name" type="text" class="inputField" id="exampleInputName1" v-model="AuthorDto.name">
            </div>
            <div class="form-group">
                <label>Surname</label>
                <input name="surname" type="text" class="inputField" id="exampleInputSurname1" v-model="AuthorDto.surname">
            </div>
            <div class="form-group">
                <label>Username</label>
                <input name="username" type="text" class="inputField" id="exampleInputUsername1" v-model="AuthorDto.username" required>
            </div>
        </div>
            <button type="submit" class="button">Create</button>
        </form>
    </div>
</template>
    
<script>
import axios from "axios";

export default {
    data() {
        return {
            AuthorDto: {
                name: '',
                surname: '',
                username: '',
            },
            error: null
        }
    },
    methods: {
        async Register(){
            try{
                const response = await axios.post("http://localhost:9090/authors", this.AuthorDto, {withCredentials: true})
                console.log(response.data)
                this.$router.push({name: "home"})
                window.alert("Author created successfully!")

            }catch(error){
                if(error.response){
                    console.log("Error response body: ", error.response.data)
                    this.error = error.response.data.message
                }else{
                    console.log("Error: ", error.message)
                    this.error = error.message
                }
            }
        }
    }
}
</script>