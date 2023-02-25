// @ts-ignore
const theme_items = document.querySelectorAll('.theme-item');

//load theme
getTheme();

theme_items.forEach((theme) => {
    theme.addEventListener("click", (e) => {
        //remove all active classes
        theme_items.forEach((theme) => {
            theme.classList.remove('outline');
            theme.classList.remove('outline-2');
        });

        //add active class to clicked theme
        // @ts-ignore
        e.target.closest('.theme-item').classList.add('outline');
        // @ts-ignore
        e.target.closest('.theme-item').classList.add('outline-2');
        // @ts-ignore
        const theme = e.target.closest('.theme-item').getAttribute('data-set-theme');
        document.documentElement.setAttribute('data-theme', theme);

        //save theme to local storage
        saveTheme(theme);
    });
});

//save theme to local storage
function saveTheme(theme: string) {
    localStorage.setItem('theme', theme);
}

//get theme from local storage
function getTheme() {
    if (localStorage.getItem('theme') !== null) {
        // @ts-ignore
        document.documentElement.setAttribute('data-theme', localStorage.getItem('theme'));
    } else {
        document.documentElement.setAttribute('data-theme', 'night');
    }
}