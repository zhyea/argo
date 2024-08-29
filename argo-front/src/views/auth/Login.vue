<!-- -->
<!-- 登录页面 -->
<template>

	<div class="login-register-container">
		<div class="container right-panel-active" ref="containerRef">
			<div class="container__form container--signup">
				<el-form action="#" class="form" id="form1">
					<h2 class="form__title">Register</h2>
					<el-input size="large" type="text" placeholder="User" class="input"/>
					<el-input size="large" type="email" placeholder="Email" class="input"/>
					<el-input size="large" type="password" placeholder="Password" class="input"/>
					<el-button size="large" class="btn" @click="submitSignUp">Submit</el-button>
				</el-form>
			</div>

			<div class="container__form container--signin">
				<el-form action="#" class="form" id="form2">
					<h2 class="form__title">Login</h2>
					<el-input size="large" type="email" placeholder="Email" class="input"/>
					<el-input size="large" type="password" placeholder="Password" class="input"/>
					<a href="#" class="link">Forgot your password?</a>
					<el-button size="large" class="btn" @click="submitSignIn">Submit</el-button>
				</el-form>
			</div>

			<div class="container__overlay">
				<div class="overlay">
					<div class="overlay__panel overlay--left">
						<el-button size="large" round class="btn" id="signIn" @click="handleSignIn">Login</el-button>
					</div>
					<div class="overlay__panel overlay--right">
						<el-button size="large" round class="btn" id="signUp" @click="handleSignUp">Register</el-button>
					</div>
				</div>
			</div>
		</div>
	</div>


<!--
	<div class="login-right-warp">
		<div class="login-right-warp-title">Login</div>

		<div class="login-right-warp-form">

			<div class="login-right-warp-form_title">用户名登录</div>

			<el-form ref="authFormRef"
			         :model="authForm"
			         :rules="authRules"
			         status-icon label-width="auto" class="login-form">

				<el-form-item prop="username">
					<el-input v-model="authForm.username"
					          placeholder="请输入账号"
					          autocomplete="off"
					          :prefix-icon="User"/>
				</el-form-item>

				<el-form-item prop="password">
					<el-input v-model="authForm.password"
					          type="password"
					          autocomplete="off"
					          placeholder="请输入密码"
					          :prefix-icon="Lock"/>
				</el-form-item>


				<el-form-item>
					<el-button type="primary" @click="submitForm(authFormRef)">登录</el-button>
				</el-form-item>
			</el-form>

			<div class="login-msg">
				* 温馨提示：建议使用谷歌、Microsoft Edge上浏览器，360浏览器请使用极速模式
			</div>
		</div>
	</div>-->

</template>


<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {User, Lock} from '@element-plus/icons-vue';


const router = useRouter();
const authFormRef = ref();


const authForm = ref({
	username: '',
	password: '',
});


const authRules = {
	username: [
		{required: true, message: '请输入账号', trigger: 'blur'},
	],
	password: [
		{required: true, message: '请输入密码', trigger: 'blur'},
		{min: 6, max: 16, message: '密码长度在6到16个字符', trigger: 'blur'},
	],
};




const containerRef=ref()


const handleSignIn = () => {
	containerRef.value.classList.remove("right-panel-active");
}


const handleSignUp = () => {
	containerRef.value.classList.add("right-panel-active");
}

const submitSignUp = (e) => {
	e.preventDefault()
}

const submitSignIn = (e) => {
	e.preventDefault()
}


</script>


<style scoped>

.login-register-container{
	--white: #e9e9e9;
	--gray: #333;
	--blue: #0367a6;
	--lightblue: #008997;
	--button-radius: 0.7rem;
	height: 66vh;
	width: 96vw;
}

.container {

	background-color: var(--white);
	border-radius: var(--button-radius);
	box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
	0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
	height: 50vh;
	width: 50vw;
	overflow: hidden;
	position: relative;
	left: 30vw;
	top:10vh

}


.form__title {
	font-weight: 300;
	margin: 0 0 1.25rem;
}

.link {
	color: var(--gray);
	font-size: 0.9rem;
	margin: 1.5rem 0;
	text-decoration: none;
}


.container__form {
	height: 100%;
	position: absolute;
	top: 0;
	transition: all 0.6s ease-in-out;
}

.container--signin {
	left: 0;
	width: 50%;
	z-index: 2;
}

.container.right-panel-active .container--signin {
	transform: translateX(100%);
}

.container--signup {
	left: 0;
	opacity: 0;
	width: 50%;
	z-index: 1;
}

.container.right-panel-active .container--signup {
	animation: show 0.6s;
	opacity: 1;
	transform: translateX(100%);
	z-index: 5;
}

.container__overlay {
	height: 100%;
	left: 50%;
	overflow: hidden;
	position: absolute;
	top: 0;
	transition: transform 0.6s ease-in-out;
	width: 50%;
	z-index: 100;
}

.container.right-panel-active .container__overlay {
	transform: translateX(-100%);
}

.overlay {
	background: var(--lightblue) url("../../assets/images/loginbg.jpg") no-repeat fixed center;
	background-size: cover;
	height: 100%;
	left: -100%;
	position: relative;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 200%;
}

.container.right-panel-active .overlay {
	transform: translateX(50%);
}

.overlay__panel {
	align-items: center;
	display: flex;
	flex-direction: column;
	height: 100%;
	justify-content: center;
	position: absolute;
	text-align: center;
	top: 0;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 50%;
}

.overlay--left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay--left {
	transform: translateX(0);
}

.overlay--right {
	right: 0;
	transform: translateX(0);
}

.container.right-panel-active .overlay--right {
	transform: translateX(20%);
}

.btn {
	background-color: var(--blue);
	background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
	border-radius: 20px;
	border: 1px solid var(--blue);
	color: var(--white);
	cursor: pointer;
	font-size: 0.8rem;
	font-weight: bold;
	letter-spacing: 0.1rem;
	padding: 0.9rem 4rem;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
}

.form > .btn {
	margin-top: 1.5rem;
}

.btn:active {
	transform: scale(0.95);
}

.btn:focus {
	outline: none;
}

.form {
	background-color: var(--white);
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 3rem;
	height: 100%;
	text-align: center;
}

.input {
	background-color: #fff;
	border: none;
	//padding: 0.9rem 0.9rem;
	margin: 0.5rem 0;
	width: 100%;
}

@keyframes show {

	0%,
	49.99% {
		opacity: 0;
		z-index: 1;
	}

	50%,
	100% {
		opacity: 1;
		z-index: 5;
	}
}

</style>