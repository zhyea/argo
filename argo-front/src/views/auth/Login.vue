<!-- -->
<!-- 登录页面 -->
<template>

	<div class="login-register-container">

		<div class="container right-panel-active" ref="containerRef">

			<div class="container-form container-register">

				<el-form action="#" class="form" id="registerForm">
					<h2 class="form-title">注册</h2>
					<el-input size="large" type="text" placeholder="User" class="input"/>
					<el-input size="large" type="text" placeholder="Email" class="input"/>
					<el-input size="large" type="password" placeholder="Password" class="input"/>
					<el-button size="large" class="btn" @click="submitSignUp">提交</el-button>
				</el-form>
			</div>

			<div class="container-form container-login">

				<el-form ref="loginFormRef"
				         :model="loginForm"
				         :rules="loginRules"
				         status-icon label-width="auto" class="form" action="#" id="loginForm">

					<h2 class="form-title">登录</h2>

					<el-form-item prop="username">
						<el-input v-model="loginForm.username"
						          placeholder="用户名"
						          autocomplete="off"
						          :prefix-icon="User" class="input"/>
					</el-form-item>


					<el-form-item prop="password">
						<el-input v-model="loginForm.password"
						          type="password"
						          autocomplete="off"
						          placeholder="密码"
						          :prefix-icon="Lock" class="input"/>
					</el-form-item>


					<a href="#" class="link">忘记密码?</a>
					<el-button size="large" class="btn" @click="submitSignIn">提交</el-button>
				</el-form>
			</div>

			<div class="container-overlay">
				<div class="overlay">
					<div class="overlay-panel overlay-left">
						<el-button id="signUp" size="large" round class="btn"
						           @click="handleRegister">
							注册
						</el-button>
					</div>
					<div class="overlay-panel overlay--right">
						<el-button id="signIn" size="large" round class="btn"
						           @click="handleLogin">
							登录
						</el-button>
					</div>
				</div>
			</div>
			<!--   end of form container   -->
		</div>
	</div>

</template>


<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {User, Lock} from '@element-plus/icons-vue';


const router = useRouter();
const loginFormRef = ref();


const loginForm = ref({
	username: '',
	password: '',
});


const loginRules = {
	username: [
		{required: true, message: '请输入用户名', trigger: 'blur'},
	],
	password: [
		{required: true, message: '请输入密码', trigger: 'blur'},
		{min: 6, max: 16, message: '密码长度在6到16个字符', trigger: 'blur'},
	],
};


const containerRef = ref()


const handleRegister = () => {
	containerRef.value.classList.remove("right-panel-active");
}


const handleLogin = () => {
	containerRef.value.classList.add("right-panel-active");
}

const submitSignUp = (e) => {
	e.preventDefault()
}

const submitSignIn = (e) => {
	e.preventDefault()
}


</script>


<style lang="less" scoped>

.login-register-container {
	--white: #e9e9e9;
	--gray: #333;
	--blue: #0367a6;
	--lightblue: #008997;
	--deepgreen: #2b4b6b;
	--button-radius: 3px;

	height: 360px;
	width: 720px;
	position: relative;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}

.container {
	background-color: var(--deepgreen);
	box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25), 0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
	height: 100%;
	width: 100%;
	overflow: hidden;
}


.form-title {
	font-weight: 300;
	margin: 0 0 1.25rem;
}

.link {
	color: var(--gray);
	font-size: 0.9rem;
	margin: 1.5rem 0;
	text-decoration: none;
}


.container-form {
	height: 100%;
	position: absolute;
	top: 0;
	transition: all 0.6s ease-in-out;
}

.container-register {
	left: 0;
	opacity: 0;
	width: 50%;
	z-index: 2;
}

.container-login {
	left: 0;
	width: 50%;
	z-index: 1;
}

.container.right-panel-active .container-register {
	animation: show 0.6s;
	opacity: 0;
	transform: translateX(100%);
}

.container.right-panel-active .container-login {
	transform: translateX(100%);
	z-index: 5;
}

.container-overlay {
	height: 100%;
	left: 50%;
	overflow: hidden;
	position: absolute;
	top: 0;
	transition: transform 0.6s ease-in-out;
	width: 50%;
	z-index: 100;
}

.container.right-panel-active .container-overlay {
	transform: translateX(-100%);
}

.overlay {
	background: var(--lightblue) url("../../assets/auth-background.png") no-repeat fixed center;
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

.overlay-panel {
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

.overlay-left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
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

.el-input {
	border-radius: 0;
}

.input {
	background-color: #fff;
	border: none;
	margin: 0.5rem 0;
	width: 100%;
	border-radius: 0;
}

.el-form-item {
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