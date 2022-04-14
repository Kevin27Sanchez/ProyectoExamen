unit umain;

interface

uses
  System.SysUtils, System.Types, System.UITypes, System.Classes, System.Variants,
  FMX.Types, FMX.Controls, FMX.Forms, FMX.Graphics, FMX.Dialogs, System.Rtti,
  FMX.Grid.Style, FMX.Memo, FMX.Controls.Presentation, FMX.ScrollBox,
  FMX.StdCtrls, FMX.Layouts, FMX.ListBox, FMX.Edit, FMX.Grid,StrUtils,
  FMX.Memo.Types;

type
  TForm5 = class(TForm)
    Panel1: TPanel;
    Button1: TButton;
    Button2: TButton;
    Button3: TButton;
    Panel2: TPanel;
    Hoja1: TPanel;
    Hoja2: TPanel;
    Hoja3: TPanel;
    Rejilla: TStringGrid;
    mmoDatos: TMemo;
    Panel3: TPanel;
    Label2: TLabel;
    Edit2: TEdit;
    lista: TListBox;
    Button4: TButton;
    Button5: TButton;
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
    procedure Button5Click(Sender: TObject);
    procedure mmoDatosChange(Sender: TObject);
  private
    { Private declarations }
    Columnas : array[1..18] of TStringColumn;
  public
    { Public declarations }
  end;

var
  Form5: TForm5;

implementation

{$R *.fmx}

procedure TForm5.Button1Click(Sender: TObject);
begin
  hoja1.Visible:=true;
  hoja2.Visible:=false;
  hoja3.Visible:=false;
end;

procedure TForm5.Button2Click(Sender: TObject);
begin
  hoja1.Visible:=false;
  hoja2.Visible:=true;
  hoja3.Visible:=false
end;

procedure TForm5.Button3Click(Sender: TObject);
begin
  hoja1.Visible:=false;
  hoja2.Visible:=false;
  hoja3.Visible:=true
end;

procedure TForm5.Button4Click(Sender: TObject);
var linea,celda  : string;
    titulo : string;
    i,j,n    : integer;
begin
  //mmoDatos ....
  Linea:=mmoDatos.Lines[0];
  titulo:='';
  n:=0;
  for i := 1 to length(linea) do
    begin
      if Copy(linea,i,1)<>'	' then  //chr(8)
        titulo := titulo+Copy(linea,i,1)
      else
        begin
          n:=n+1;
          if n<19 then
            begin
              Columnas[n]:=TStringcolumn.Create(Form5);
              Columnas[n].Header:=titulo;
              Rejilla.AddObject(Columnas[n]);
              titulo:='';
            end;
        end;
    end;
  Rejilla.RowCount:=mmodatos.Lines.Count-1;

  for j:=1 to mmodatos.Lines.Count-1 do   //mmodatos.Lines-1
   begin
      Linea:=mmoDatos.Lines[j];
      titulo:='';
      n:=0;
      for i := 1 to length(linea) do
        begin
          if Copy(linea,i,1)<>'	' then  //chr(8)
            titulo := titulo+Copy(linea,i,1)
          else
            begin
              n:=n+1;
              if n<18 then
                begin
                  Rejilla.Cells[n-1,j-1]:=Titulo;
                  titulo:='';
                end;
            end;
        end;
   end;


end;

procedure TForm5.Button5Click(Sender: TObject);
var numerodatos,y,fila:integer;  descrip : string;
begin
    numerodatos := mmoDatos.Lines.Count-1;

    lista.Clear;

    //If ContainsText(uppercase(descrip), uppercase(edtCaserio.text)) Then
         // begin
           // showmessage('OK');
          end;

   procedure TForm5.mmoDatosChange(Sender: TObject);
begin

end;

// y := 0;
   // For fila := 0 To numerodatos do
    //  begin
     //   descrip := rejilla.Cells[5-1,fila];
       // If ContainsText(uppercase(descrip), uppercase(edtCaserio.text)) Then
         // begin
          //showmessage(descrip);
           // lista.add
           // lista.List(y, 0) = Hoja1.Cells(fila, 1).Value
           // lista.List(y, 1) = Hoja1.Cells(fila, 2).Value
           // lista.List(y, 2) = Hoja1.Cells(fila, 4).Value
           // lista.List(y, 3) = Hoja1.Cells(fila, 5).Value
           // lista.List(y, 4) = Hoja1.Cells(fila, 8).Value
           // y := y + 1;
         // end;
     // end;

end.
