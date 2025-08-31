import config from "@/config";
import {fixSideMenu} from "@/utils/helper";

const TOKEN = 'token:'
const PERMISSION = 'permissions:'
const LOCALE = 'locale:'
const APP_LIST = 'appList:'
const CURRENT_APP = 'current_app:'
const CURRENT_APP_MENU = 'current_app_menu:'
const CACHE_KEY = 'cache:'


export function setLocale(lang: string) {
	return sessionStorage.setItem(getLocaleKey(), lang)
}

export async function getLocale() {
	return sessionStorage.getItem(getLocaleKey())
}

function getLocaleKey() {
	return LOCALE + config.guard
}

export function cacheToken(token: string) {
	return sessionStorage.setItem(keyOfToken(), token)
}

export function getCachedToken(): string {
	try {
		const token = sessionStorage.getItem(keyOfToken());
		return token || '';
	} catch (error) {
		console.error('Failed to get cached token:', error);
		throw new Error('Failed to retrieve cached token');
	}
}

export function removeCachedToken() {
	return sessionStorage.removeItem(keyOfToken())
}

export function keyOfToken() {
	return TOKEN + config.guard
}


export function cacheAppList(appList: Array<any>) {
	return sessionStorage.setItem(APP_LIST, JSON.stringify(appList))
}


export function getCachedAppList() {
	const json = sessionStorage.getItem(APP_LIST)
	return json ? JSON.parse(json) : []
}


export function cacheCurrentApp(app: any) {
	if (!app) {
		return;
	}
	return sessionStorage.setItem(CURRENT_APP, JSON.stringify(app))
}


export function getCachedCurrentApp() {
	const json = sessionStorage.getItem(CURRENT_APP)
	if (json) {
		return JSON.parse(json);
	}
	return null;
}


export function clearCachedAppInfo() {
	sessionStorage.removeItem(APP_LIST)
	sessionStorage.removeItem(CURRENT_APP)
}


export function cacheEnums(enums: any) {
	if (enums) {
		sessionStorage.setItem(CACHE_KEY, JSON.stringify(enums))
	}
}


export function getCachedEnums() {
	const json = sessionStorage.getItem(CACHE_KEY)
	if (json) {
		return JSON.parse(json);
	}
	return null;
}
