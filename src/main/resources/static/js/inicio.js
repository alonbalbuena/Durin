const botonMenu = document.querySelector(".boton-navegacion");

botonMenu.addEventListener("click", () => {
  const menuNavegacion = document.querySelector(".navegacion");
  menuNavegacion.classList.add("navegacion--desplegado");
});

document.querySelector(".wrapper").addEventListener("click", () => {
  const menuNavegacion = document.querySelector(".navegacion");

  if (menuNavegacion.classList.contains("navegacion--desplegado"))
    menuNavegacion.className = "navegacion";
});

document.querySelectorAll(".navegacion__titulo-apartado").forEach((elemento) =>{
	const hermano = elemento.nextElementSibling;
	elemento.addEventListener("click", () => {
		if(hermano.classList.contains("navegacion__contenido-apartado--desplegado")){
			hermano.className = "navegacion__contenido-apartado";
		}else{
			hermano.classList.add("navegacion__contenido-apartado--desplegado");
		}
  })}
);

document.querySelector("input[type='checkbox']").addEventListener("click",()=>{

	if(document.querySelector("input[type='checkbox']").checked){
		document.documentElement.style.setProperty('--amarillo', '#A60815');
		document.documentElement.style.setProperty('--azul', '#730606');
		document.documentElement.style.setProperty('--blanco', '#F2F2F2');
		document.documentElement.style.setProperty('--gris', '#8C8C8C');
	}else{
		document.documentElement.style.setProperty('--amarillo', '#fca311');
		document.documentElement.style.setProperty('--azul', '#14213d');
		document.documentElement.style.setProperty('--blanco', '#ffffff');
		document.documentElement.style.setProperty('--gris', '#e5e5e5');
	}
});



