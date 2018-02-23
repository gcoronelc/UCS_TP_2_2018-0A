
create view v_alumnosmat(
cursoprog, ciclo, idcurso, curso, horario, activo, 
idprofesor, profesor, idalumno, alumno,
parcial, final, subsanacion, promedio
) as
select 
cp.IdCursoProg cursoprog,
cp.IdCiclo idciclo,
c.IdCurso idcurso, c.NomCurso nomcurso,
cp.Horario horario,
cp.activo activo,
p.IdProfesor idprofesor,
p.ApeProfesor + ', ' + p.NomProfesor profesor,
a.IdAlumno idalumno,
concat(a.ApeAlumno,', ',a.NomAlumno) alumno,
m.ExaParcial parcial, m.ExaFinal final, 
m.ExaSub subsanacion, m.Promedio promedio
from curso c
join CursoProgramado cp on c.IdCurso = cp.IdCurso
join Matricula m on cp.IdCursoProg = m.IdCursoProg 
join Alumno a on m.IdAlumno = a.IdAlumno
left join Profesor p on cp.IdProfesor = p.IdProfesor;


select * from v_alumnosmat;


