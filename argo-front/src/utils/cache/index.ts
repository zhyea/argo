import config from "@/config";

const TOKEN = 'token:'
const PERMISSION = 'permissions:'
const LOCALE = 'locale:'
const APP_LIST = 'appList:'
const CURRENT_APP = 'current_app:'


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
		return sessionStorage.getItem(keyOfToken());
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


export function cacheAppList(appList: Arra<any>) {
	return sessionStorage.setItem(APP_LIST, appList)
}


export function getCachedAppList() {
	return sessionStorage.getItem(APP_LIST)
}


export function cacheCurrentApp(app: any) {
	return sessionStorage.setItem(CURRENT_APP, app)
}


export function getCachedCurrentApp() {
	return sessionStorage.getItem(CURRENT_APP)
}
