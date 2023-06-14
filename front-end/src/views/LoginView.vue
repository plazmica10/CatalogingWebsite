<template>
<h1>Sign In</h1>
<div class="wrapper">
    <form class="kanta">
    <div class="kanturina">
        <div class="form-group">
            <label>Email</label>
            <input type="email" class="" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="" id="exampleInputPassword1">
        </div>
    </div>
        <button type="submit" class="button" v-on:submit="">Sign in</button>
    </form>
</div>
</template>


<script>

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