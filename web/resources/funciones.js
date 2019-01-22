function validarFormulario(boton)
{
    var formulario = document.getElementById("formulario1");
    var accion = document.getElementById("accion");
    if(boton.value=="agregar")
    {
        accion.value="agregar";
    }
    else if(boton.value=="modificar")
    {
        if(this.validaUnicoCheckbox())
        {
            accion.value="modificar";   
        }
        else
        {
            alert("Debe seleccionar un unico elemento a editar");
        }
    }
    else if(boton.value=="eliminar")
    {
        accion.value="eliminar";
    }
    formulario.submit();
}

//Validacion de solo un checkbox seleccionado
function validaUnicoCheckbox()
{
  //Recuperamos la forma y el arreglo de checkboxes
  var forma = document.getElementById("formulario1");
  var personas = forma.usuario;

  //Verificamos si se selecciono mas de un elemento
  contadorCheckboxes = 0;

  //Verificamos que sea un arreglo
  if(personas.length > 0)
  {
    //Incrementamos el contador por cada
    for(i=0; i<personas.length; i++)
    {
      if( personas[i].checked )
      {
        contadorCheckboxes++;
      }
    }
  }
  else
  {
    //Esto sucede cuando solo hay un elemento en la tabla, hay que recordar
    //que dinamicamente podrian eliminarse las personas y solo dejar un registro
    //por lo que ya no genera un arreglo en javascript, sino un checkbox aislado
    if(personas != null && personas.checked)
    {
      contadorCheckboxes++;
    }
  }
  //Solo se debio haber seleccionado un elemento
  if(contadorCheckboxes == 1)
    return true;
  else
    return false;
}

