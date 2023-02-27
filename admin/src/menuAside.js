import {
  mdiAccountCircle,
  mdiMonitor,
  mdiGithub,
  mdiLock,
  mdiAlertCircle,
  mdiSquareEditOutline,
  mdiTable,
  mdiViewList,
  mdiTelevisionGuide,
  mdiResponsive,
  mdiPalette,
  mdiReact,
  mdiHome,
} from "@mdi/js";

export default [
  {
    to: "/",
    icon: mdiMonitor,
    label: "Dashboard",
  },




  {

    label: "Properties",
    icon: mdiHome,
    menu: [
      {
        label: "Show Properties",
        to: "/properties",
      },
      {
        label: "New property",
        to: "/add-property",
      },
    ],
  },
  {
    to: "/forms",
    label: "Forms",
    icon: mdiSquareEditOutline,
  },

  {
    to: "/login",
    label: "Login",
    icon: mdiLock,
  },

  {
    label: "Dropdown",
    icon: mdiViewList,
    menu: [
      {
        label: "Item One",
      },
      {
        label: "Item Two",
      },
    ],
  },

  {
    to: "/profile",
    label: "Profile",
    icon: mdiAccountCircle,
  },

];
