package riscv
import chisel3._

class CD extends Module{
	val io = IO(new Bundle{
		val RType = Input(UInt(1.W))
		val Load = Input(UInt(1.W))
		val Store = Input(UInt(1.W))
		val SBType = Input(UInt(1.W))
		val IType = Input(UInt(1.W))
		val Jalr = Input(UInt(1.W))
		val Jal = Input(UInt(1.W))
		val Lui = Input(UInt(1.W))
		val MemWrite = Output(UInt(1.W))
		val Branch = Output(UInt(1.W))
		val MemRead = Output(UInt(1.W))
		val RegWrite = Output(UInt(1.W))
		val MemToReg = Output(UInt(1.W))
		val AluOp = Output(UInt(3.W))
		val Operand_aSel = Output(UInt(2.W))
		val Operand_bSel = Output(UInt(1.W))
		val ExtendSel = Output(UInt(2.W))
		val NextPcSel = Output(UInt(2.W))
	})

		
		
		io.MemWrite := 0.U
		io.Branch := "b0".U
		io.MemRead := 0.U
		io.RegWrite := 0.U
		io.MemToReg := 0.U
		io.AluOp := "b000".U
		io.Operand_aSel := "b00".U
		io.Operand_bSel := 0.U
		io.ExtendSel := "b00".U
		io.NextPcSel := "b00".U


		when(io.RType === 1.U){
			io.RegWrite := 1.U
		}.elsewhen(io.Load === 1.U){
			io.MemRead := 1.U
			io.RegWrite := 1.U
			io.MemToReg := 1.U
			io.AluOp := "b100".U
			io.Operand_bSel := 1.U
		}.elsewhen(io.Store === 1.U){
			io.MemWrite := 1.U
			io.AluOp := "b101".U
			io.Operand_bSel := 1.U
			io.ExtendSel := "b10".U
		}.elsewhen(io.SBType === 1.U){
			io.Branch := "b1".U
			io.AluOp := "b010".U
			io.NextPcSel := "b01".U
		}.elsewhen(io.IType === 1.U){
			io.RegWrite := 1.U
			io.AluOp := "b001".U
			io.Operand_bSel := 1.U		
		}.elsewhen(io.Jalr === 1.U){
			io.RegWrite := 1.U
			io.AluOp := "b011".U
			io.Operand_aSel := "b10".U
			io.NextPcSel := "b11".U
		}.elsewhen(io.Jal === 1.U){
			io.RegWrite := 1.U
			io.AluOp := "b011".U
			io.Operand_aSel := "b10".U
			io.NextPcSel := "b10".U
			
		}.elsewhen(io.Lui === 1.U){
			io.RegWrite := 1.U
			io.AluOp := "b110".U
			io.Operand_aSel := "b11".U
			io.Operand_bSel := 1.U
			io.ExtendSel := "b01".U
		}
}
