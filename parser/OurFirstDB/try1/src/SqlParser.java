/* Generated By:JavaCC: Do not edit this line. SqlParser.java */
import java.util.List;
import java.util.ArrayList;
public class SqlParser implements SqlParserConstants {

    private List<Token> tokens = new ArrayList<Token>();

    public static void main(String[] args) throws Exception {
        SqlParser parser = new SqlParser(System.in);
        parser.parse();
        System.out.println("sql is correct!");
    }

  final public void parse() throws ParseException {
    Token t;
    tokens = new ArrayList<Token>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SELECT:
      jj_consume_token(SELECT);
        t = new Token();
        t.image = "select";
        System.out.println(t.image);
        tokens.add(t);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ALL:
      case DISTINCT:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DISTINCT:
          jj_consume_token(DISTINCT);
          break;
        case ALL:
          jj_consume_token(ALL);
          break;
        default:
          jj_la1[0] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[1] = jj_gen;
        ;
      }
      selectResultList();
      jj_consume_token(FROM);
      fromTables();
      where();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STATEMENT_END:
        jj_consume_token(STATEMENT_END);
        break;
      case END:
        jj_consume_token(END);
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case INSERT:
      jj_consume_token(INSERT);
      jj_consume_token(INTO);
      jj_consume_token(ID);
      jj_consume_token(VALUES);
      jj_consume_token(LBRACKET);
      jj_consume_token(ID);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 43:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_1;
        }
        jj_consume_token(43);
        jj_consume_token(ID);
      }
      jj_consume_token(RBRACKET);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STATEMENT_END:
        jj_consume_token(STATEMENT_END);
        break;
      case END:
        jj_consume_token(END);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case VALUES:
      jj_consume_token(VALUES);
      jj_consume_token(LBRACKET);
      values();
      jj_consume_token(RBRACKET);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STATEMENT_END:
        jj_consume_token(STATEMENT_END);
        break;
      case END:
        jj_consume_token(END);
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case UPDATE:
      jj_consume_token(UPDATE);
      jj_consume_token(ID);
      jj_consume_token(SET);
      sets();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WHERE:
        where();
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STATEMENT_END:
        jj_consume_token(STATEMENT_END);
        break;
      case END:
        jj_consume_token(END);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case DELETE:
      delete();
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//ArrayList<Token> saveToken() :
//{
//    Token t;
//    tokens = new ArrayList<Token>();
//}
//{
//    t = new Token();
//    t.image = "select";
//    System.out.println(t.image);
//    tokens.add(t);
//    return tokens;
//}
  final public void delete() throws ParseException {
    Token t;
        System.out.println("------DELETE METHOD --------");
    t = jj_consume_token(DELETE);
            System.out.println(t.image);
            tokens.add(t);
    t = jj_consume_token(FROM);
        System.out.println(t.image);
        tokens.add(t);
    jj_consume_token(ID);

    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHERE:
      where();
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STATEMENT_END:
      jj_consume_token(STATEMENT_END);
      break;
    case END:
      jj_consume_token(END);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void selectResultList() throws ParseException {
    selectResult();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_2;
      }
      jj_consume_token(43);
      selectResult();
    }
  }

  final public void selectResult() throws ParseException {
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOT:
      jj_consume_token(DOT);
      jj_consume_token(ID);
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AS:
      jj_consume_token(AS);
      jj_consume_token(ID);
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
  }

  final public void fromTables() throws ParseException {
    table();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_3;
      }
      jj_consume_token(43);
      table();
    }
  }

  final public void table() throws ParseException {
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      jj_consume_token(ID);
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
  }

  final public void where() throws ParseException {
    jj_consume_token(WHERE);
    multiCondition();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case GROUP_BY:
    case ORDER_BY:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ORDER_BY:
        orderBy();
        break;
      case GROUP_BY:
        groupBy();
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
  }

  final public void multiCondition() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      condition();
      break;
    case LBRACKET:
      jj_consume_token(LBRACKET);
      condition();
      jj_consume_token(RBRACKET);
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
      case AND:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        jj_consume_token(AND);
        break;
      case OR:
        jj_consume_token(OR);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        condition();
        break;
      case LBRACKET:
        jj_consume_token(LBRACKET);
        condition();
        jj_consume_token(RBRACKET);
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void condition() throws ParseException {
    name();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQ:
    case GT:
    case LT:
    case NE:
    case LIKE:
      simpleCondition();
      break;
    case BETWEEN:
      betweenCondition();
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void simpleCondition() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQ:
      jj_consume_token(EQ);
      break;
    case GT:
      jj_consume_token(GT);
      break;
    case LT:
      jj_consume_token(LT);
      break;
    case NE:
      jj_consume_token(NE);
      break;
    case LIKE:
      jj_consume_token(LIKE);
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    rightCondition();
  }

  final public void betweenCondition() throws ParseException {
    jj_consume_token(BETWEEN);
    rightCondition();
    jj_consume_token(AND);
    rightCondition();
  }

  final public void name() throws ParseException {
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOT:
      jj_consume_token(DOT);
      jj_consume_token(ID);
      break;
    default:
      jj_la1[24] = jj_gen;
      ;
    }
  }

  final public void rightCondition() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING:
      jj_consume_token(STRING);
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      break;
    case ID:
      function();
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void function() throws ParseException {
    jj_consume_token(ID);
    jj_consume_token(LBRACKET);
    arguments();
    jj_consume_token(RBRACKET);
  }

  final public void arguments() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      argument();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 43:
          ;
          break;
        default:
          jj_la1[26] = jj_gen;
          break label_5;
        }
        jj_consume_token(43);
        argument();
      }
      break;
    default:
      jj_la1[27] = jj_gen;
      ;
    }
  }

  final public void argument() throws ParseException {
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING:
      jj_consume_token(STRING);
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void values() throws ParseException {
    value();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[29] = jj_gen;
        break label_6;
      }
      jj_consume_token(43);
      value();
    }
  }

  final public void value() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING:
      jj_consume_token(STRING);
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      break;
    default:
      jj_la1[30] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void orderBy() throws ParseException {
    jj_consume_token(ORDER_BY);
    name();
  }

  final public void groupBy() throws ParseException {
    jj_consume_token(GROUP_BY);
    name();
  }

  final public void sets() throws ParseException {
    set();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        ;
        break;
      default:
        jj_la1[31] = jj_gen;
        break label_7;
      }
      jj_consume_token(43);
      set();
    }
  }

  final public void set() throws ParseException {
    jj_consume_token(ID);
    jj_consume_token(EQ);
    rightCondition();
  }

  /** Generated Token Manager. */
  public SqlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[32];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1010000,0x1010000,0x0,0x0,0x0,0x0,0x400000,0x0,0x807800,0x400000,0x0,0x0,0x0,0x8000,0x0,0x0,0x300000,0x300000,0x2000000,0x80000000,0x80000000,0x2000000,0x78000000,0x78000000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x600,0x800,0x600,0x600,0x0,0x600,0x0,0x0,0x600,0x800,0x8,0x0,0x800,0x10,0x0,0x0,0x10,0x1,0x1,0x10,0x6,0x2,0x8,0x130,0x800,0x10,0x120,0x800,0x120,0x800,};
   }

  /** Constructor with InputStream. */
  public SqlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SqlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public SqlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public SqlParser(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[44];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 32; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 44; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}