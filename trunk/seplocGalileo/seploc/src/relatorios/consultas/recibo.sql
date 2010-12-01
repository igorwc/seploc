SELECT r.intNumreq, r.datData, c.intClienteId, c.vcrCnpj, c.vcrCpf,
c.vcrRazao, c.vcrCep, c.vcrEnder, c.vcrBairro,c.vcrCidade,c.vcrEstado,
p.intCodProj, p.vcrProjeto
FROM tbl_reqserv r,tbl_clientes c, tbl_projetos p
WHERE
r.intNumreq = $P{CodReq} and  r.intCodProj = p.intCodProj
and p.intClienteId = c.intClienteId


select * from
(
(select li.intNumreq as numReq, li.vcrNomeArq as item, li.dblFormato as formato ,
li.dblDimensao as dimensao,pa.vcrNome as nomePapel,li.vcrImpressao as impressao,li.intQuant as qtd, li.dblValorUnit as subTotal,
 li.dblValorSubUnit as valorItem, li.intNumLin as linha
from tbl_linhareq li,tbl_papel pa, tbl_reqserv rr
where li.intCodPap = pa.intCodPap
and rr.intNumReq = $P{ReqNum}
and rr.intNumReq = li.intNumReq)
union
(SELECT r.intNumreq as numReq,op.vcrNomeItem as item,null as formato ,null as dimensao , null as nomePapel ,  null as impressao,
rso.intQuant as qtd ,  (op.dblValorItem*rso.intQuant) as subTotal, op.dblValorItem as valorItem, null as linha
FROM tbl_reqserv r,tbl_opcionaisreqserv op, tbl_reqservopcionais rso
WHERE
rso.intNumReq = r.intNumReq and
rso.intCodOp = op.intCod and
r.intNumReq = $P{ReqNum}  )
) as tabela3