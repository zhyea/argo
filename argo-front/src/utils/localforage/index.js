import localforage from "localforage";


const TOKEN = 'token:'

const PERMISSION = 'permissions:'

const LOCALE = 'locale:'

export const setLocale = (lang) => {
	return localforage.setItem(getLocaleKey(), lang)
}

export const getLocale = () => {
	return localforage.getItem(getLocaleKey())
}

const getLocaleKey = () => {
	return LOCALE + config.guard
}